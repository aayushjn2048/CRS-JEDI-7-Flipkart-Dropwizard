package com.crs.flipkart.constants;

/**
 * The enum Gender.
 */
public enum Gender {

    /**
     * Male gender.
     */
    MALE(1),
    /**
     * Female gender.
     */
    FEMALE(2),
    /**
     * Other gender.
     */
    OTHER(3);
    private final int gender;

    private Gender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        final String name = name();
        return name;
    }

    /**
     * Gets name.
     *
     * @param val the val
     * @return the name
     */
    public static Gender getName(int val) {
        Gender gender = Gender.OTHER;
        switch (val) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
        }
        return gender;
    }

    /**
     * String to gender gender.
     *
     * @param val the val
     * @return the gender
     */
    public static Gender stringToGender(String val) {
        Gender gender = Gender.OTHER;
        if (val.equalsIgnoreCase("male"))
            gender = Gender.MALE;
        else if (val.equalsIgnoreCase("female"))
            gender = Gender.FEMALE;
        else if (val.equalsIgnoreCase("other"))
            gender = Gender.OTHER;
        return gender;
    }

}
