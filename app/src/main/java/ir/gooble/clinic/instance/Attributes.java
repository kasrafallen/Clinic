package ir.gooble.clinic.instance;

import ir.gooble.clinic.R;

public class Attributes {

    public final static String NAME = "کلینیک پوست و چشم شمس";

    public final static String FIELD_ABOUT_DOCTORS = "معرفی پزشکان";
    public final static String FIELD_RESERVE = "تعیین وقت";
    public final static String FIELD_GALLERY = "گالری";
    public final static String FIELD_NEW_FACTS = "تازه های پزشکی";
    public final static String FIELD_ADD_ACCOUNT = "ثبت پرونده";
    public final static String FIELD_ABOUT_CLINIC = "اطلاعات کلینیک";

    public final static String[] MAIN_FIELDS = new String[]
            {
                    FIELD_ABOUT_DOCTORS
                    , FIELD_RESERVE
                    , FIELD_GALLERY
                    , FIELD_NEW_FACTS
                    , FIELD_ADD_ACCOUNT
                    , FIELD_ABOUT_CLINIC
            };

    public static int getResource(String field) {
        switch (field) {
            case Attributes.FIELD_ABOUT_CLINIC:
                return R.mipmap.about_clinic;
            case Attributes.FIELD_ABOUT_DOCTORS:
                return R.mipmap.about_docs;
            case Attributes.FIELD_ADD_ACCOUNT:
                return R.mipmap.add_account;
            case Attributes.FIELD_RESERVE:
                return R.mipmap.reserve;
            case Attributes.FIELD_NEW_FACTS:
                return R.mipmap.new_facts;
            case Attributes.FIELD_GALLERY:
                return R.mipmap.gallery;
        }
        return 0;
    }
}
