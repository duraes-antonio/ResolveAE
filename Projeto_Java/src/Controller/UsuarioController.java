package Controller;

import AplicationService.UsuarioApl;

public class UsuarioController {
    private UsuarioApl aplication = null;
    public UsuarioController(){
        this.aplication = new UsuarioApl();
    }
//    public Usuario login(String email, String senha) throws AuthenticationException {
//        Usuario usuarioSessao = this.aplication.processLogin(email,senha);
//        if (usuarioSessao == null){
//            throw new AuthenticationException("Email e/ou senha incorretos");
//        }
//        return usuarioSessao;
//    }
}
