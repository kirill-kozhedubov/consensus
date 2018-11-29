package iq.ven.portal.consensus.common.beans;

import iq.ven.portal.consensus.common.model.base.UserData;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProjectUser implements Serializable {

   public ProjectUser() {
      UserData userData = new UserData();
      setUserData(userData);
   } //!TODO REMOVE ITS FOR TEST

   private UserData userData;

   public UserData getUserData() {
      return userData;
   }

   public void setUserData(UserData userData) {
      this.userData = userData;
   }

   public void clear() {

   }
}
