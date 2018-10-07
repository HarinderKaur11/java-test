package com.h2rd.refactoring.usermanagement;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlRootElement
@Getter
@Setter
public class User {

    String name;
    String email;
    List<String> roles;
}
