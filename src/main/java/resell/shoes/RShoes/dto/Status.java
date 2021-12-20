package resell.shoes.RShoes.dto;

public enum Status {
    ALREADY(1),
    READY(2),
    START(3),
    ARRIVE(4);

    private final int code;

    public int getCode(){
        return code;
    }
    Status(int code){
        this.code = code;
    }
}
