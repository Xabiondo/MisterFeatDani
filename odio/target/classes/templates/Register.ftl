<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>


</head>
<body>
<div class="login-container">
    <h2>Registro</h2>
    <p>Por favor, completa el siguiente formulario para crear una cuenta</p>
    <form action="/register" method="POST">
        <div class="input-group">
            <label for="usuario">Nombre de usuario</label>
            <input type="text" id="usuario" name="usuario" required>
        </div>

        <div class="input-group">
            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit">Registrar</button>
    </form>

</div>

</body>
</html>
