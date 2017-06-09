package pi_pf.negocio;
import java.io.IOException;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import pi_pf.beans.Pessoa;
import pi_pf.persistencia.PessoaDAO;


public class ControleAcessoController implements AuthenticationSuccessHandler  {


		private Pessoa pessoa;
		

		public Pessoa getPessoa() {
			return pessoa;
		}

		public void setPessoa(Pessoa pessoa) {
			this.pessoa = pessoa;
		}
		
		
		public ControleAcessoController(){
			pessoa = new Pessoa();
			SecurityContext context = SecurityContextHolder.getContext();
			if(context instanceof SecurityContext)
			{
				Authentication authentication = context.getAuthentication();
				if(authentication instanceof Authentication)
				{
					 pessoa.setEmail(((User)authentication.getPrincipal()).getUsername());
				}
			}
		}

		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
			pessoa = PessoaDAO.porEmail(authentication.getName());
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			if(roles.contains("ROLE_ADMINISTRADOR")){
				response.sendRedirect("admin/principal.xhtml");
			}else if (roles.contains("ROLE_CLIENTE")){
				response.sendRedirect("cliente/forma_de_pagamento.xhtml");
			}
			//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", pessoa);
		}
	}