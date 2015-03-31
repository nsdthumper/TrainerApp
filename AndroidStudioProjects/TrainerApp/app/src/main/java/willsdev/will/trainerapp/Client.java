package willsdev.will.trainerapp;

import android.media.Image;

/**
 * Created by will on 3/12/15.
 */
public class Client {
    private String name;
    private int age;
    final private int DEFAULT = 1;
    private String clientImage;

    public Client(String name, int age) {
        this.name = name;
        if (!(age <= 0)) {
            this.age = age;
        }
        else {
            this.age = DEFAULT;
        }
        this.clientImage = " ";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (!(age <= 0)) {
            this.age = age;
        }
    }

    public void setClientImage(String id){
        this.clientImage = id;
    }

    public String getClientImage(){
        return clientImage;
    }

    @Override
    public String toString(){
    return String.format("%s,%d", name, age);
    }
}
