import axios from 'axios'

export class ClienteService{
    cosulta_url = 'http://localhost:8099/prueba/Cliente/';
    
    
    getAll(){
        return axios.get(this.cosulta_url+'listar2').then(res => res.data);
    }
    
    save(persona){
        return axios.post(this.cosulta_url+'guardar',persona).then(res => res.data)
    }

    eliminar(id){
        const url_aux = `eliminar?iden=${id}`;
        return axios.delete(this.cosulta_url+url_aux).then(res => res.data);
    }
}