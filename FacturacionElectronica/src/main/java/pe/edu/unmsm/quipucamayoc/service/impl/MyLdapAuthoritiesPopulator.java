package pe.edu.unmsm.quipucamayoc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;
import pe.edu.unmsm.quipucamayoc.persistence.UsuarioPersistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrador on 08/07/2016.
 */
@Component("myLDAPAuthPopulator")
public class MyLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

	@Autowired
	private UsuarioPersistence usuarioPersistence;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String username) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<String> listaRoles = usuarioPersistence.obtenerRoles(username);
		for(String role : listaRoles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}