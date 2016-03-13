package enums

/**
 * Created by jitin on 11/2/16.
 */
enum Visibility {

    PUBLIC('Public'),
    PRIVATE('Private')

    static Visibility getVisibility(String visibility) {

        String visibilityInUpperCase = visibility.toUpperCase()

        return valueOf(visibilityInUpperCase)
    }

    final String value

    Visibility(String value) {
        this.value = value
    }

    @Override
    String toString() { value }

    String getKey() { name() }

}