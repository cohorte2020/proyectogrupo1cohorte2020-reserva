import React,{useState} from 'react';
//import styled from 'styled-components';
import {Formulario, Label} from './elementos/Formularios';
//import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheckCircle, faExclamationTriangle}  from '@fortawesome/free-solid-svg-icons';
import Input from './component/Input';

//import logo from './logo.svg';
//import './App.css';

// componente
const App = () => {
  const [usuario, cambiarUsuario] = useState({campo: '', valido: null});
  const [password, cambiarPassword] = useState({campo: '', valido: null});
  const [password2, cambiarPassword2] = useState({campo: '', valido: null});

  const expresiones= {
    correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
    password: /^.{8,12}$/
  }

  return (
    <main>
        <Formulario action="">
          <Input
              estado= {usuario}
              cambiarEstado= {cambiarUsuario}
              tipo="text"
              label="Usuario"
              placeholder="sucorreo@suservidor.com"
              name= "usuario"
              leyendaError="debe contener @"
              expresionRegular="{exoresiones.correo}"
          
          />
        </Formulario>
    
    </main>
    
  );
}

export default App;
