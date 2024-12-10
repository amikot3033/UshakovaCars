import java.util.Objects;

public class CarModelDTO {
    private static int idCounter = 0;
    private Integer id;
    private String brand;
    private String modelName;
    private String countryOriginal;
    private String countryCode;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCountryOriginal() {
        return this.countryOriginal;
    }

    public void setCountryOriginal(String countryOriginal) {
        this.countryOriginal = countryOriginal;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public CarModelDTO(String brand, String modelName, String countryOriginal, String countryCode) {
        this.id = ++idCounter;
        this.brand = brand;
        this.modelName = modelName;
        this.countryOriginal = countryOriginal;
        this.countryCode = countryCode;
    }

    public String toString() {
        return " id=" + this.id + ", brand='" + this.brand + "', modelName='" + this.modelName + "', countryOriginal='" + this.countryOriginal + "', countryCode='" + this.countryCode + "' ";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            CarModelDTO that = (CarModelDTO)o;
            return Objects.equals(this.brand, that.brand) && Objects.equals(this.modelName, that.modelName);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.brand, this.modelName});
    }

    public boolean equalsId(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            CarModelDTO that = (CarModelDTO)o;
            return Objects.equals(this.id, that.id);
        } else {
            return false;
        }
    }

    public int hashCodeId() {
        return Objects.hash(new Object[]{this.id});
    }
}

