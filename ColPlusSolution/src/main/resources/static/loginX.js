document.addEventListener('DOMContentLoaded', function () {
    console.log("Script cargado");  // Verificación de que el script se ha cargado correctamente.

    const loginForm = document.getElementById('login-form'); // Formulario de inicio de sesión

    loginForm.addEventListener('submit', function (event) {
        event.preventDefault();  // Previene el envío por defecto del formulario.
        console.log("Formulario enviado");  // Mensaje de depuración para confirmar envío.

        // Obtener valores del formulario
        const username = document.getElementById('user_login').value;
        const password = document.getElementById('user_pass').value;
        const captchaResponse = grecaptcha.getResponse();  // Obtener la respuesta de reCAPTCHA

        // Verifica si el usuario completó el CAPTCHA
        if (!captchaResponse) {
            document.getElementById('message').textContent = "Por favor, verifica que no eres un robot.";
            return;  // Termina la ejecución si no se completó el CAPTCHA.
        }

        // Realizar la solicitud de inicio de sesión al servidor con el CAPTCHA incluido
        axios.post(`http://localhost:8082/user/loginvalidation?username=${username}&password=${password}`)
        .then(response => {
            console.log("Respuesta recibida del servidor");  // Confirmación de respuesta

            if (response.status === 200) {
                document.getElementById('message').textContent = "Login exitoso";
                window.location.href = 'paginaPrincipal.html';  // Redirigir a la página principal
            } else {
                document.getElementById('message').textContent = "Credenciales incorrectas";
            }
        })
        .catch(error => {
            console.log("Error en la solicitud");  // Mensaje de depuración en caso de error

            if (error.response && error.response.status === 401) {
                document.getElementById('message').textContent = "Credenciales incorrectas";
            } else {
                document.getElementById('message').textContent = "Ocurrió un error en el servidor";
            }
        });
    });
});

