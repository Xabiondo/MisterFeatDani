<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Panel de Administración - Fantasy Liga</title>


</head>
<body>

<div class="navbar">
    <div><strong style="color: #FFD700;">FantasyFútbol</strong></div>
    <div>
      <a href="/interfaz">Inicio</a>
      <a href="/poner-subasta">Subastar</a>
      <a href="/Mercado">Mercado</a>
      <a href="/Equipo">Mi Equipo</a>
      <a href="/Ayuda">Ayuda</a>
      <a href="/logout">Cerrar sesión</a>
    </div>
  </div>

  <div class="container">
    <h1>Panel de Administración</h1>

    <!-- Sección: Crear nuevo usuario -->
    <div class="section">
      <h2>Crear Usuario con Privilegios</h2>
      <form class="form-inline" method="post" action="/admin/create">
        <input type="text" name="nombre" placeholder="Usuario" required>
        <input type="password" name="password" placeholder="Contraseña" required>
        <select name="rol">
          <option value="usuario">Usuario</option>
          <option value="admin">Admin</option>
        </select>
        <button type="submit">Crear</button>
      </form>
    </div>

    <!-- Sección: Lista de usuarios y acciones -->
    <div class="section">
      <h2>Usuarios Registrados</h2>
      <#if usuarios?size gt 0>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Dinero (€)</th>
              <th>Modificar</th>
              <th>Eliminar</th>
            </tr>
          </thead>
          <tbody>
            <#list usuarios as usuario>
              <tr>
                <td>${usuario.id}</td>
                <td>${usuario.nombre}</td>
                <td>
                  <form method="post" action="/admin/update/${usuario.id}" style="display:inline;">
                    <input type="number" name="dinero" value="${usuario.dinero?string}" min="0">
                    <button type="submit" class="update-btn">OK</button>
                  </form>
                </td>
                <td>
                  <form method="post" action="/admin/delete/${usuario.id}" onsubmit="return confirm('Eliminar ${usuario.nombre}?');" style="display:inline;">
                    <button type="submit" class="delete-btn">X</button>
                  </form>
                </td>
              </tr>
            </#list>
          </tbody>
        </table>
      <#else>
        <div class="no-users">No hay usuarios registrados.</div>
      </#if>
    </div>

  </div>
</body>
</html>
