/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.accessGoogle;

/**
 *
 * @author PC
 */
public class GooglePojo {

    private String id;
    private String email;
    private String name;

    public GooglePojo() {
    }

    public GooglePojo(String id, String email, boolean verified_email, String name, String given_name, String family_name, String link, String picture) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
}
