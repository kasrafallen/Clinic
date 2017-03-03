package ir.gooble.clinic.instance;

import ir.gooble.clinic.R;

public class Attributes {

    public final static String NAME = "کلینیک پوست و چشم شمس";
    public static final String DOCTOR_NAME = "دکتر حمیدرضا حسنی";
    public static final String DOC_DESCRIPTION = "فوق تخصص قرنیه\n و بیماریهای سطحی چشم";

    public static final String DESCRIPTION = "خدمات کامل در زمینه پوست و زیبایی و همچنین متخصص در زمینه درمانبیماریهای چشمی";
    public static final String ADDRESS = "تهران - خیابان ولیعصر - بالاتر از ونک - ایستگاه نیایش - خیابان بهرامی - پلاک 81 - واحد 5";

    public static final String WEBSITE_INFO = "clinic.com";
    public static final String TELEGRAM_INFO = "@shamsclinic";
    public static final String INSTAGRAM_INFO = "shams_clinic";
    public static final String PHONE_INFO = "021-88456734";
    public static final String EMAIL_INFO = "info@shamsclinic.ir";

    public static int getResource(String field) {
        switch (field) {
            case Attributes.FIELD_ABOUT_CLINIC:
                return R.mipmap.z_about_clinic;
            case Attributes.FIELD_ABOUT_DOCTORS:
                return R.mipmap.z_about_docs;
            case Attributes.FIELD_ADD_ACCOUNT:
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
}
