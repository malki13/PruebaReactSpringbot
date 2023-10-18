import axios from 'axios'

export class HotelService{
    cosulta_url = 'http://localhost:8099/prueba/hotel/';
    
    getAll(){
        return axios.get(this.cosulta_url+'listar2').then(res => res.data);
    }

    getAllHabitaciones(id){
        // Habitaciones?idHotel=9
        const url_aux = `Habitaciones?idHotel=${id}`;
        return axios.get(this.cosulta_url+url_aux).then(res => res.data);
    }
    
    save(persona){
        return axios.post(this.cosulta_url+'guardar',persona).then(res => res.data)
    }

    save2(persona){
        return axios.post(this.cosulta_url+'guardar2',persona).then(res => res.data)
    }

    update(persona){
        return axios.put(this.cosulta_url+'actualizar',persona).then(res => res.data)
    }

    eliminar(id){
        const url_aux = `eliminar?iden=${id}`;
        return axios.delete(this.cosulta_url+url_aux).then(res => res.data);
    }
}