import { Component } from 'react';
import { HotelService } from '../services/HotelReservas';
import { DataTable } from 'primereact/datatable'
import { Column } from 'primereact/column';
import { Panel } from 'primereact/panel';
import { Menubar } from 'primereact/menubar';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';
import { InputNumber } from 'primereact/inputnumber';
        

import 'primereact/resources/themes/nova-alt/theme.css'
import 'primereact/resources/primereact.min.css'
import 'primeicons/primeicons.css'


export default class Hoteles extends Component {

    constructor() {
        super();

        this.state = {
            visible: false,
            hotel: {
                iden: null,
                nombre: null,
                descripcion: null,
                direccion: null,
                habitaciones:0,
            },
            updatehotel: {
                iden: null,
                nombre: null,
                descripcion: null,
                direccion: null,
            },
            
            toast: null,
            hotelSeleccionado: {
            },
            tipoAct:false,
            actuaView:true,
        };

        this.items = [
            {
                label: 'Nuevo',
                icon: 'pi pi-fw pi-plus',
                command: () => { this.mostrarDialogo() }
            },
            {
                label: 'Editar',
                icon: 'pi pi-fw pi-pencil',
                command: () => { this.mostrarEditar() }
            },
            {
                label: 'Eliminar',
                icon: 'pi pi-fw pi-trash',
                command: () => { this.eliminarHotel() }
            }
        ];
        this.hotelService = new HotelService();
        this.guardadHotel = this.guardadHotel.bind(this);
        this.eliminarHotel = this.eliminarHotel.bind(this)
        this.updateHotel = this.updateHotel.bind(this)

        this.footer = (
            <div>
                <Button label='Guardar' icon='pi pi-check' onClick={this.guardadHotel} />
                <Button label='Actualizar' icon='pi pi-check' onClick={this.updateHotel} />
            </div>
        );

    }

    componentDidMount() {
        this.hotelService.getAll().then(data => this.setState({ hoteles: data }))
        this.setState({ toast: this.toast });

    }

    guardadHotel() {
        this.hotelService.save2(this.state.hotel).then(data => {
            this.setState({
                visible: false
            });
            this.state.toast.show({
                severity: 'success',
                summary: 'Éxito',
                detail: 'Hotel guardado correctamente',
            });
            this.hotelService.getAll().then(data => this.setState({ hoteles: data }))
        })
    }

    updateHotel() {
        this.hotelService.update(this.state.hotel).then(data => {
            this.setState({
                visible: false
            });
            this.state.toast.show({
                severity: 'success',
                summary: 'Éxito',
                detail: 'Actualizado guardado correctamente',
            });
            this.hotelService.getAll().then(data => this.setState({ hoteles: data }))
        })
    }

    eliminarHotel() {
        if (window.confirm("Desea Eliminar registro")) {
            this.hotelService.eliminar(this.state.hotelSeleccionado.iden).then(data => {
                this.state.toast.show({
                    severity: 'success',
                    summary: 'Éxito',
                    detail: 'Hotel Eliminado correctamente',
                });
                this.hotelService.getAll().then(data => this.setState({ hoteles: data }))
            });
        }
    }

    render() {
        return (
            <div style={{ width: '80%', marginTop: '5%', margin: '0 auto' }}>
                <Menubar model={this.items} />
                <br />
                <Panel header="HOTELES" >
                    <DataTable value={this.state.hoteles} selectionMode="single" selection={this.state.hotelSeleccionado} onSelectionChange={(e) => this.setState({ hotelSeleccionado: e.value })}>
                        <Column field='iden' header="ID"></Column>
                        <Column field='nombre' header="NOMBRE"></Column>
                        <Column field='direccion' header="DIRECCION"></Column>
                        <Column field='descripcion' header="DESCRIPCION"></Column>
                    </DataTable>
                </Panel>
                <Dialog header="Crear Hotel" visible={this.state.visible} style={{ width: '50%' }} footer={this.footer} modal={true} onHide={() => this.setState({ visible: false })}>
                    <form id='persona-form'>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                            <InputText value={this.state.hotel.nombre} id='nombre' onChange={(e) => {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let hotel = Object.assign({}, prevState.hotel);
                                    hotel.nombre = valor
                                    return { hotel }
                                })
                            }
                            } />
                            <label htmlFor='Nombre'>Nombre</label>
                        </span>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                            <InputText value={this.state.hotel.descripcion} id='descripcion' onChange={(e) => {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let hotel = Object.assign({}, prevState.hotel);
                                    hotel.descripcion = valor
                                    return { hotel }
                                })
                            }
                            } />
                            <label htmlFor='Descripcion'>Descripcion</label>
                        </span>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                            <InputText value={this.state.hotel.direccion} id='direccion' onChange={(e) => {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let hotel = Object.assign({}, prevState.hotel);
                                    hotel.direccion = valor
                                    return { hotel }
                                })
                            }
                            } />
                            <label htmlFor='Direccion'>Direccion</label>
                        </span>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                            {/* <InputNumber value={this.state.hotel.habitaciones} id='habitaciones' onChange={(e) => {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let hotel = Object.assign({}, prevState.hotel);
                                    hotel.habitaciones = valor
                                    return { hotel }
                                })
                            }
                            } /> */}
                            <InputNumber inputId="integeronly" value={this.state.hotel.habitaciones} onValueChange={(e) => 
                            {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let hotel = Object.assign({}, prevState.hotel);
                                    hotel.habitaciones = valor
                                    return { hotel }
                                })
                            }
                            } />
                            <label htmlFor='Habitaciones'>Habitaciones</label>
                        </span>
                        {/* <Button visible={this.state.tipoAct} label='Actualizar' icon='pi pi-check' onClick={this.guardadHotel2} /> */}
                        {/* <Button visible={this.state.actuaView} label='Guardar' icon='pi pi-check' onClick={this.guardadHotel} /> */}
                    </form>

                </Dialog>
                <Toast ref={(el) => (this.toast = el)} />
            </div>
        );
    }

    mostrarDialogo() {
        this.setState({
            visible: true,
            hotel: {
                iden: null,
                nombre: null,
                descripcion: null,
                direccion: null,
                habitaciones:0,
            }
        });
        // document.getElementById('persona-form').onreset();
    }

    mostrarEditar() {
        this.setState({
            visible: true,
            tipoAct:true,
            actuaView:false,
            updatehotel: {
                iden: this.state.hotelSeleccionado.iden,
                nombre: this.state.hotelSeleccionado.nombre,
                descripcion: this.state.hotelSeleccionado.descripcion,
                direccion: this.state.hotelSeleccionado.direccion,
            },
            hotel: {
                iden: this.state.hotelSeleccionado.iden,
                nombre: this.state.hotelSeleccionado.nombre,
                descripcion: this.state.hotelSeleccionado.descripcion,
                direccion: this.state.hotelSeleccionado.direccion,
            }

        })
    }



}