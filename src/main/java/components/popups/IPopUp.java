package components.popups;

public interface IPopUp<T> {

    T popUpShouldNotBeVisible() throws Exception;

    T popUpShouldBeVisible() throws Exception;
}
