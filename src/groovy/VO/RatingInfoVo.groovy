package VO

/**
 * Created by jitin on 24/2/16.
 */
class RatingInfoVo {

    Integer totalVotes
    Integer averageScore
    Integer totalScore

    @Override
    String toString()
    {
        return "${totalVotes}, ${averageScore}, ${totalScore}"
    }
}
