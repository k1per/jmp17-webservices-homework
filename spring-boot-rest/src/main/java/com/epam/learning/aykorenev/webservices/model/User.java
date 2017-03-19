package com.epam.learning.aykorenev.webservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Anton_Korenev on 3/18/2017.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
}
