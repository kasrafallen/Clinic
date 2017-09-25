package ir.gooble.clinic.oracle;

public interface CallBack {

    void onResponse(String response);

    void onError(String error);

    void onInternet();

    void onBefore();

    void onClick();
}
