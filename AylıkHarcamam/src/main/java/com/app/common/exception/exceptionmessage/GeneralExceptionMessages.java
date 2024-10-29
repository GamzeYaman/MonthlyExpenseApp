package com.app.common.exception.exceptionmessage;

public enum GeneralExceptionMessages implements BaseErrorMessage{
    EMPTY_DATA("boş olamaz!"),
    SALARY_NOT_FOUND("İlgili maaş bilgisi bulunamadı!"),
    USER_NOT_FOUND("İgili kişi bulunamadı!"),
    USERNAME_ALREADY_USED(" kullanıcı adı ile bir kullanıcı mevcuttur!"),
    SALARY_ALREADY_USED(" ayına ait bir maaş bilgisi bulunmaktadır.")
    ;

    private final String errorMessage;

    GeneralExceptionMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
