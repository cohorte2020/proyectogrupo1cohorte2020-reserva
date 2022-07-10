import React from 'react';

import { faCheckCircle,faTimesCircle, faExclamationTriangle}  from '@fortawesome/free-solid-svg-icons';
import {Input, Label, GrupoInput, UserError, PassError, IconValidacion, Boton, MensajeExito} 
from './../elementos/Formularios';


const ComponenteInput = ({estado, cambiarEstado,label, name, placeholder, tipo, leyendaError, expresionRegular}) => {
    const onChange= (e) =>{
        cambiarEstado({...estado, campo: e.target.value});
    }
    const validacion=() => {
        if (expresionRegular){
            if(expresionRegular.test(estado.campo)){
                cambiarEstado({...estado, valido: 'true'});
            }else {
                cambiarEstado({...estado, valido: 'false'})
                
            }
        }


    }

    return (
        <div>
            <Label htmlForm={name} valido= {estado.valido}>{label}</Label>
            <GrupoInput>
                <Input
                     type={tipo} 
                     placeholder={placeholder} 
                     id={name}
                     value={estado.campo}
                     onChange={onChange}
                     onKeyUp={validacion}
                     onBlur={validacion}
                     valido={estado.valido}

                />
                <IconValidacion
                    icon ={estado.valido === 'true' ?  faCheckCircle: faTimesCircle} 
                    valido={estado.valido}/>
            </GrupoInput>
            <UserError valido={estado.valido}>{leyendaError}</UserError>
        </div>    
    );



}

export default ComponenteInput;