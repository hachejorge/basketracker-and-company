package clasesVO;

/**
 * Clase que representa un usuario en el sistema.
 * Contiene información básica como nombre de usuario, correo electrónico y contraseña.
 */
public class UsuarioVO {
    
    /** El nombre de usuario del usuario en el sistema. */
    private String nombreUsuario;
    
    /** El correo electrónico asociado al usuario. */
    private String correoElect;
    
    /** La contraseña del usuario. */
    private String password;

    /**
     * Constructor para crear un nuevo usuario con los valores proporcionados.
     * 
     * @param nombreUsuario El nombre de usuario del nuevo usuario.
     * @param correoElect El correo electrónico del nuevo usuario.
     * @param password La contraseña del nuevo usuario.
     */
    public UsuarioVO(String nombreUsuario, String correoElect, String password) {
        this.nombreUsuario = nombreUsuario;
        this.correoElect = correoElect;
        this.password = password;
    }

    // Getters y Setters

    /**
     * Obtiene el nombre de usuario del usuario.
     * 
     * @return El nombre de usuario del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario del usuario.
     * 
     * @param nombreUsuario El nombre de usuario del usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return El correo electrónico del usuario.
     */
    public String getCorreoElect() {
        return correoElect;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param correoElect El correo electrónico del usuario.
     */
    public void setCorreoElect(String correoElect) {
        this.correoElect = correoElect;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Representación en cadena de texto del objeto {@code UsuarioVO}.
     * 
     * @return La representación en cadena del objeto.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", correoElect='" + correoElect + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
