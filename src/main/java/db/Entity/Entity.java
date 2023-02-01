package db.Entity;

public class Entity {
    private long id;

    public void setId(long id){
        this.id = id;
    }

    public long getId (){
        return id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
