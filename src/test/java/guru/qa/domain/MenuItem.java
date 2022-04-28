package guru.qa.domain;

public enum MenuItem {
    IMG("Картинки"), VIDEO("Видео");
    public final String rusName;

    MenuItem(String rusName) {
        this.rusName = rusName;
    }
}
