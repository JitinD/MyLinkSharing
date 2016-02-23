package enums

/**
 * Created by jitin on 11/2/16.
 */
enum Visibility {

    PUBLIC,
    PRIVATE

    static getVisibility(String visibility) {

        String visibilityInLowerCase = visibility.toLowerCase()

        switch (visibility) {
            case "public":
                return Visibility.PUBLIC
                break

            case "private":
                return Visibility.PRIVATE
                break
        }

    }

}