package com.ttnd.linksharing

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class UserRole implements Serializable {

	private static final long serialVersionUID = 1

	User secUser
	Role secRole

	UserRole(User u, Role r) {
		this()
		secUser = u
		secRole = r
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof UserRole)) {
			return false
		}

		other.secUser?.id == secUser?.id && other.secRole?.id == secRole?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (secUser) builder.append(secUser.id)
		if (secRole) builder.append(secRole.id)
		builder.toHashCode()
	}

	static UserRole get(long secUserId, long secRoleId) {
		criteriaFor(secUserId, secRoleId).get()
	}

	static boolean exists(long secUserId, long secRoleId) {
		criteriaFor(secUserId, secRoleId).count()
	}

	private static DetachedCriteria criteriaFor(long secUserId, long secRoleId) {
		UserRole.where {
			secUser == User.load(secUserId) &&
			secRole == Role.load(secRoleId)
		}
	}

	static UserRole create(User secUser, Role secRole, boolean flush = false) {
		def instance = new UserRole(secUser: secUser, secRole: secRole)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(User u, Role r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UserRole.where { secUser == u && secRole == r }.deleteAll()

		if (flush) { UserRole.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(User u, boolean flush = false) {
		if (u == null) return

		UserRole.where { secUser == u }.deleteAll()

		if (flush) { UserRole.withSession { it.flush() } }
	}

	static void removeAll(Role r, boolean flush = false) {
		if (r == null) return

		UserRole.where { secRole == r }.deleteAll()

		if (flush) { UserRole.withSession { it.flush() } }
	}

	static constraints = {
		secRole validator: { Role r, UserRole ur ->
			if (ur.secUser == null || ur.secUser.id == null) return
			boolean existing = false
			UserRole.withNewSession {
				existing = UserRole.exists(ur.secUser.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['secUser', 'secRole']
		version false
	}
}
