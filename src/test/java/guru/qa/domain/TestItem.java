package guru.qa.domain;

public enum TestItem {
    Catalog("Каталог"), News("Новости"),
    Automarket("Автобарахолка"), HousesAndApartments("Дома и квартиры"),
    Services("Услуги"), Market("Барахолка"),
    Forum("Форум")
    ;
    public final String headerName;
    TestItem(String rusName) {
        this.headerName = rusName;
    }
}
