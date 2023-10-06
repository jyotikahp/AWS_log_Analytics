//SJSU CS 218 SPRING 2023 TEAM4
package sjsu.cs218.news.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AccessService {
    boolean isAuthenticated();
}
