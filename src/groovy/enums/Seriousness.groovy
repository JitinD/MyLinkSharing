package enums

/**
 * Created by jitin on 11/2/16.
 */
enum Seriousness {
    SERIOUS('Serious'),
    VERY_SERIOUS('Very serious'),
    CASUAL('Casual')


    static Seriousness getSeriousness(String seriousness) {

        String seriousnessInUpperCase = seriousness.toUpperCase()

        return valueOf(seriousnessInUpperCase)

    }

    final String value

    Seriousness(String value) {
        this.value = value
    }

    @Override
    String toString() { value }

    String getKey() { name() }
}