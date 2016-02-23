package enums

/**
 * Created by jitin on 11/2/16.
 */
enum Seriousness {
    SERIOUS,
    VERY_SERIOUS,
    CASUAL


    static getSeriousness(String seriousness) {

        String seriousnessInLowerCase = seriousness.toLowerCase()

        switch (seriousnessInLowerCase) {
            case "serious":
                return Seriousness.SERIOUS
                break

            case "very_serious": return Seriousness.VERY_SERIOUS
                break

            case "casual": return Seriousness.CASUAL
                break
        }

    }
}