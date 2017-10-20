package ir.gooble.clinic.instance;

import ir.gooble.clinic.R;

public class Attributes {

    public static int getResource(String field) {
        switch (field) {
            case Attributes.FIELD_ABOUT_CLINIC:
                return R.mipmap.z_about_clinic;
            case Attributes.FIELD_ABOUT_DOCTORS:
                return R.mipmap.z_about_docs;
            case Attributes.FIELD_REGISTER:
                return R.mipmap.z_add_account;
            case Attributes.FIELD_RESERVE:
                return R.mipmap.z_reserve;
            case Attributes.FIELD_NEW_FACTS:
                return R.mipmap.z_new_facts;
            case Attributes.FIELD_GALLERY:
                return R.mipmap.z_gallery;
        }
        return 0;
    }

    public final static String FIELD_ABOUT_DOCTORS = "معرفی پزشکان";
    public final static String FIELD_RESERVE = "تعیین وقت";
    public final static String FIELD_GALLERY = "گالری";
    public final static String FIELD_NEW_FACTS = "تازه های پزشکی";
    public final static String FIELD_REGISTER = "ثبت پرونده";
    public final static String FIELD_ABOUT_CLINIC = "اطلاعات کلینیک";

    public final static String[] MAIN_FIELDS = new String[]
            {
                    FIELD_ABOUT_DOCTORS
                    , FIELD_RESERVE
                    , FIELD_GALLERY
                    , FIELD_NEW_FACTS
                    , FIELD_REGISTER
                    , FIELD_ABOUT_CLINIC
            };

    public static final String[] SUPPORTED_INSURANCES = new String[]{
            "بیمه ندارد"
            , "تامین اجتماعی"
            , "نیروهای مسلح"
    };
}
