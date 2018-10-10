package com.h2rd.refactoring.usermanagement;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@XmlRootElement
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude={"name","roles"}) //Equal and hashcode implemente of unique field email
public class User {
	
    private String name;
    private String email;
    private List<String> roles;
}
