import axios from 'axios'

export class ReservasService{
    cosulta_url = 'http://localhost:8099/prueba/Cliente/';
    
    getAll(){
        return axios.get(this.cosulta_url+'listarReserva').then(res => res.data);
    }
    
    save(persona){
        return axios.post(this.cosulta_url+'reservar',persona).then(res => res.data)
    }

    save2(reserva){
        return axios.post(this.cosulta_url+'reservar',reserva).then(res => res.data)
    }

    eliminar(id){
        const url_aux = `eliminarReserva?iden=${id}`;
        return axios.delete(this.cosulta_url+url_aux).then(res => res.data);
    }
}