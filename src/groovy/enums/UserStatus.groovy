package enums

/**
 * Created by jitin on 11/3/16.
 */
enum UserStatus {
    ACTIVE_USERS('Active users'),
    INACTIVE_USERS('Inactive users'),
    ALL_USERS('All users')

    final String value

    UserStatus(String value) {
        this.value = value
    }

    @Override
    String toString() { value }

    String getKey() { name() }
}