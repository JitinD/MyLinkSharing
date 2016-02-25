package enums

/**
 * Created by jitin on 11/2/16.
 */
enum Visibility {

    PUBLIC,
    PRIVATE

    static Visibility getVisibility(String visibility) {

        String visibilityInUpperCase = visibility.toUpperCase()

        return valueOf(visibilityInUpperCase)
    }


}