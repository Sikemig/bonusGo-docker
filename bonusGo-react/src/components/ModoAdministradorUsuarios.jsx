import { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import pigCoinLogo from "../assets/images/PigCoin_2.jpg";
import '../assets/styles/modoAdministradorGestiones.css';

import { Modal, Button, Form, Navbar, Nav, NavDropdown, Container } from 'react-bootstrap';

export default function ModoAdministradorUsuarios() {
  const [usuarios, setUsuarios] = useState([]);
  const [adminNombre, setAdminNombre] = useState('');
  const [editarId, setEditarId] = useState(null);
  const [nombre, setNombre] = useState('');
  const [apellido, setApellido] = useState('');
  const [correo, setCorreo] = useState('');
  const [telefono, setTelefono] = useState('');
  const [monedas, setMonedas] = useState(0);
  const [rolSeleccionado, setRolSeleccionado] = useState('');
  const [busqueda, setBusqueda] = useState('');
  const [filtroRol, setFiltroRol] = useState('');
  const [usuariosFiltrados, setUsuariosFiltrados] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [usuarioAEliminar, setUsuarioAEliminar] = useState(null);
  const [mostrarConfirmacion, setMostrarConfirmacion] = useState(false);
  const [roles, setRoles] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    fetchAdmin();
    fetchUsuarios();
    fetchRoles();
  }, []);

  useEffect(() => {
    filtrarUsuarios();
  }, [busqueda, filtroRol, usuarios]);

  const fetchAdmin = async () => {
    const token = localStorage.getItem('token');
    const id = localStorage.getItem('id');
    try {
      const res = await axios.get(`/usuario/${id}`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      setAdminNombre(res.data.nombre);
    } catch (err) {
      console.error('Error al obtener admin:', err);
    }
  };

  const fetchUsuarios = async () => {
    const token = localStorage.getItem('token');
    try {
      const res = await axios.get('/usuario/getTodos', {
        headers: { Authorization: `Bearer ${token}` }
      });
      setUsuarios(res.data);
    } catch (err) {
      console.error('Error al obtener usuarios:', err);
    }
  };

  const fetchRoles = async () => {
    const token = localStorage.getItem('token');
    try {
      const res = await axios.get('/roles/getAll', {
        headers: { Authorization: `Bearer ${token}` }
      });
      setRoles(res.data);
    } catch (err) {
      console.error('Error al obtener roles:', err);
    }
  };

  const filtrarUsuarios = () => {
    const texto = busqueda.toLowerCase();
    const filtrados = usuarios.filter(u =>
      u.correo.toLowerCase().includes(texto) &&
      (filtroRol === '' || u.rol?.nombre === filtroRol)
    );
    setUsuariosFiltrados(filtrados);
  };

  const obtenerNombreRol = (rol) => {
    if (!rol) return 'Desconocido';
    switch (rol.nombre) {
      case 'ROLE_ADMIN': return 'Administrador';
      case 'ROLE_USER': return 'Usuario';
      default: return rol.nombre;
    }
  };

  const prepararEdicion = (usuario) => {
    setEditarId(usuario.id_Usuario);
    setNombre(usuario.nombre);
    setApellido(usuario.apellido || '');
    setCorreo(usuario.correo);
    setTelefono(usuario.telefono || '');
    setMonedas(usuario.moneda);
    setRolSeleccionado(usuario.rol?.id_Rol.toString() || '');
    setShowModal(true);
  };

  const limpiarFormulario = () => {
    setNombre('');
    setApellido('');
    setCorreo('');
    setTelefono('');
    setMonedas(0);
    setRolSeleccionado('');
  };

  const handleGuardar = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('token');
    try {
      await axios.put(
        `/usuario/actualizar/${editarId}`,
        {
          nombre,
          apellido,
          correo,
          telefono,
          moneda: monedas,
          rol: { id_Rol: parseInt(rolSeleccionado) }
        },
        {
          headers: { Authorization: `Bearer ${token}` }
        }
      );
      fetchUsuarios();
      limpiarFormulario();
      setShowModal(false);
    } catch (err) {
      console.error('Error al actualizar usuario:', err);
    }
  };

  const prepararBorrado = (usuario) => {
    setUsuarioAEliminar(usuario);
    setMostrarConfirmacion(true);
  };

  const confirmarBorrado = async () => {
    const token = localStorage.getItem('token');
    try {
      await axios.delete(`/usuario/eliminar/${usuarioAEliminar.id_Usuario}`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      fetchUsuarios();
    } catch (err) {
      console.error('Error al eliminar usuario:', err);
    }
    setMostrarConfirmacion(false);
    setUsuarioAEliminar(null);
  };

  const irPerfil = () => navigate('/perfil');
  const handleGestionObjetivos = () => navigate('/modoAdministradorObjetivos');
  const handleGestionProductos = () => navigate('/modoAdministradorProductos');
  const handleGestion = () => navigate('/modoAdministrador');
  const handleUsuarioObjetivos = () => navigate('/objetivos');
  const handleUsuarioProducto = () => navigate('/productos');
  const handleIndexAdmin = () => navigate('/indexUsuarioAdministrador');

  return (
    <>
      <div className="contenido">
        {/* Navbar */}
        <Navbar expand="lg" bg="dark" variant="dark" className="shadow-sm">
          <Container fluid>
            <Navbar.Brand onClick={handleIndexAdmin} className="d-flex align-items-center gap-2 clickable">
              <img src={pigCoinLogo} width="40" height="40" alt="PigCoin Logo" className="rounded-circle" />
              <strong>BonusGo</strong>
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="navbar-nav" />
            <Navbar.Collapse id="navbar-nav" className="justify-content-between">
              <Nav>
                <Nav.Link className="btn-perfil nav-btn-center" onClick={handleGestion}> Menú Administrador</Nav.Link>
                <Link className="nav-link" to="/indexUsuarioAdministrador">Inicio</Link>
                <NavDropdown title="Gestión" id="gestion-dropdown">
                  <NavDropdown.Item onClick={handleGestionObjetivos}>Gestionar Objetivos</NavDropdown.Item>
                  <NavDropdown.Item onClick={handleGestionProductos}>Gestionar Productos</NavDropdown.Item>
                </NavDropdown>
                <NavDropdown title="Ver" id="ver-dropdown">
                  <NavDropdown.Item onClick={handleUsuarioObjetivos}>Ver Objetivos</NavDropdown.Item>
                  <NavDropdown.Item onClick={handleUsuarioProducto}>Ver Productos</NavDropdown.Item>
                </NavDropdown>
              </Nav>
              <div className="d-flex align-items-center gap-3 flex-wrap perfil-navbar">
                <span className="text-white fw-semibold">¡Hola, {adminNombre || 'Usuario'}!</span>
                <Button className="btn-perfil" onClick={irPerfil}>
                  Mi Perfil
                </Button>
              </div>
            </Navbar.Collapse>
          </Container>
        </Navbar>

        {/* Contenido principal */}
        <div className="admin-usuarios-wrapper">
          <div className="bienvenida">MODO ADMINISTRADOR - USUARIOS</div>

          <div className="busqueda-filtros">
            <input
              type="text"
              className="form-control"
              placeholder="Buscar usuario por correo"
              value={busqueda}
              onChange={(e) => setBusqueda(e.target.value)}
            />
            <select
              className="form-select"
              value={filtroRol}
              onChange={(e) => setFiltroRol(e.target.value)}
            >
              <option value="">Todos los roles</option>
              <option value="ROLE_USER">Usuarios</option>
              <option value="ROLE_ADMIN">Administradores</option>
            </select>
          </div>

          <div className="tabla-gestion">
            <div className="table-responsive">
              <table className="table table-hover">
                <thead>
                  <tr>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Correo</th>
                    <th>Teléfono</th>
                    <th>Rol</th>
                    <th>Monedas</th>
                    <th>Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {usuariosFiltrados.length > 0 ? (
                    usuariosFiltrados.map(usuario => (
                      <tr key={usuario.id_Usuario}>
                        <td>{usuario.nombre}</td>
                        <td>{usuario.apellido}</td>
                        <td>{usuario.correo}</td>
                        <td>{usuario.telefono}</td>
                        <td>{obtenerNombreRol(usuario.rol)}</td>
                        <td>{usuario.moneda}</td>
                        <td>
                          <button className="btn btn-primary btn-sm me-2" onClick={() => prepararEdicion(usuario)}>Editar</button>
                          <button className="btn btn-danger btn-sm" onClick={() => prepararBorrado(usuario)}>Eliminar</button>
                        </td>
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td colSpan="7" className="text-center text-muted">No se encontraron usuarios.</td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          </div>
          
        </div>
        {showModal && (
        <div className="modal show fade d-block" tabIndex="-1" style={{ backgroundColor: 'rgba(0,0,0,0.5)' }}>
          <div className="modal-dialog">
            <div className="modal-content">
              <form onSubmit={handleGuardar}>
                <div className="modal-header">
                  <h5 className="modal-title">Editar Usuario</h5>
                  <button type="button" className="btn-close" onClick={() => setShowModal(false)}></button>
                </div>
                <div className="modal-body">
                  <div className="mb-3">
                    <label className="form-label">Nombre</label>
                    <input type="text" className="form-control" value={nombre} onChange={e => setNombre(e.target.value)} required />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Apellido</label>
                    <input type="text" className="form-control" value={apellido} onChange={e => setApellido(e.target.value)} required />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Correo</label>
                    <input type="email" className="form-control" value={correo} onChange={e => setCorreo(e.target.value)} required />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Teléfono</label>
                    <input type="text" className="form-control" value={telefono} onChange={e => setTelefono(e.target.value)} />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Monedas</label>
                    <input type="number" className="form-control" value={monedas} onChange={e => setMonedas(parseInt(e.target.value))} required />
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Rol</label>
                    <select className="form-select" value={rolSeleccionado} onChange={e => setRolSeleccionado(e.target.value)} required>
                      {roles.map(rol => (
                        <option key={rol.id_Rol} value={rol.id_Rol.toString()}>
                          {obtenerNombreRol(rol)}
                        </option>
                      ))}
                    </select>
                  </div>
                </div>
                <div className="modal-footer">
                  <button type="button" className="btn btn-cancelar" onClick={() => setShowModal(false)}>Cancelar</button>
                  <button type="submit" className="btn btn-success">Guardar</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      )}
        {/* Modal Confirmación */}
        <Modal show={mostrarConfirmacion} onHide={() => setMostrarConfirmacion(false)} centered>
          <Modal.Header closeButton>
            <Modal.Title>Confirmar eliminación</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            ¿Estás seguro de que deseas eliminar a <strong>{usuarioAEliminar?.nombre}</strong>?
          </Modal.Body>
          <Modal.Footer>
            <Button variant="danger" onClick={confirmarBorrado}>Eliminar</Button>
            <Button variant="secondary" onClick={() => setMostrarConfirmacion(false)}>Cancelar</Button>
          </Modal.Footer>
        </Modal>
      </div>

      {/* Footer */}
      <footer className="footer mt-5">
        <h4>📬 BonusGo - 2025</h4>
        <div className="d-flex justify-content-center gap-4">
          <span>
            Manual de usuario -{" "}
            <a
              href="https://www.notion.so/Manual-de-Uso-de-BonusGo-1e98c574388f80908278ef5ff7136d67"
              target="_blank"
            >
              Notion BonusGo
            </a>
          </span>
          <span>
            Contacto - <a href="mailto:BonusGo@BonusGo.es">BonusGo@BonusGo.es</a>
          </span>
        </div>
      </footer>
    </>
  );
}
