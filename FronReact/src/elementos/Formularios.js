import styled, {css} from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const colores = {
    borde: "#0075ff",
    error: "#bb2929",
    exito: "#1ed12d",
}

//elemento
const Main = styled.main` 
    background-color:#9c9c9c;
    border: 3px solid black;
`;
const Formulario = styled.form `
    display: grid;
    grid-template-comlums: 1fr;   
    gap: 20px;

}
`;


const Label = styled.label`
    display: block;
    font-weight:700;
    padeing:10px;
    min-heigth: 40px;
    gap: 20px;
    cursor: pointer; 

    ${props=> props.valido === 'false' && css `
        color:${colores.error};`} 
    

`;
const GrupoInput = styled.div`
    position: relative;
    z-index:90;


`;
const Input = styled.input`
    with:50%;
    backgound: #fff;
    border-raduis: 2px;
    heigt: 45px;
    line-height:45px;
    padding: 0 20px 0 10px;
    transition: .3s ease all;
    border: 2px solid transparent;
    
    &:focus {
        border: 2px solid ${colores.borde};
        outline: none;
        box-sadow: 2px 0px rgba (163,163,163, 0.4);
    }

    ${props=>props.valido === 'true' && css `
        border: 2px solid transparent;`} 
    
        ${props=>props.valido === 'false' && css `
        border: 2px solid ${colores.error};`} !important;    
`;
const UserError = styled.p`
    font-size: 12px;
    margin-bottom:0;
    color ${colores.error};
    display: none;

    ${props=>props.valido === 'true' && css `
        display: none;
    `}
    ${props=>props.valido === 'false' && css `
        display: block;
    `}


`;
const IconValidacion =styled(FontAwesomeIcon)`
    position: absolute;
    right: 10px;
    bottom:14px;
    font-size:16px;
    opacity:0;

    ${props=> props.valido === 'false' && css `
        opacity:1;
        color: ${colores.error};
    `}

    ${props=> props.valido === 'true' && css `
    opacity:1;
    color: ${colores.exito};
`}

`;   

const PassError = styled.p `
    font-size:12px;
    margin 5px;
    color: ${colores.error};
    display: none;
`;


/*transition: .1s ease all;*/

const Boton = styled.button`
    height: 45px;
    line-height: 45px;
    width 50% ;
    font-weight: bold;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: .1s ease all;
    

    &: hover{
        box-sadow: 2px 0px rgba (163,163,163, 1); 
    }
`;
const MensajeExito = styled.p`
    font-size: 14px;
    color: ${colores.exito};
    display: none;
`;


export {
    Main,
    Formulario,
    Label, 
    GrupoInput, 
    Input, 
    UserError,
    PassError, 
    IconValidacion, 
    Boton, 
    MensajeExito
};

