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
import { TreeSelect } from 'primereact/treeselect';
import { Dropdown } from 'primereact/dropdown';
import 'primereact/resources/themes/nova-alt/theme.css'
import 'primereact/resources/primereact.min.css'
import 'primeicons/primeicons.css'
import { ReservasService } from '../services/Reservas';


export default class Reservas extends Component {

    constructor() {
        super();

        this.state = {
            visible: false,
            reserva: {
                iden: null,
                entrada: null,
                salida: null,
                cliente: 0,
                habitaciones:0,
            },
            hotel: {
                iden: null,
                nombre: null,
                descripcion: null,
                direccion: null,
            },
            toast: null,
            habitaciones: [],
            habitacionesSeleccionadas:{},
            reservaSeleccionado: {},
            hotelSeleccionado: {},
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
        this.reservasService = new ReservasService();
        this.hotelService = new HotelService();
        this.guardadHotel = this.guardadHotel.bind(this);
        this.loadRoomsByHotel = this.loadRoomsByHotel.bind(this);
        this.onHotelSelectChange = this.onHotelSelectChange.bind(this);
        // this.eliminarHotel = this.eliminarHotel.bind(this)

        this.footer = (
            <div>
                <Button label='Guardar' icon='pi pi-check' onClick={this.guardadHotel} />
            </div>
        );

    }

    componentDidMount() {
        this.reservasService.getAll().then(data => this.setState({ reservas: data }))
        // this.hotelService.getAll().then(data => this.setState({ hoteles: data }))
        this.hotelService.getAll().then(data => {
            const hotelesData = data.map(hotel => ({
                key: hotel.iden.toString(), // Convierte el ID en una cadena
                label: hotel.nombre,
            }));
            this.setState({ hoteles: hotelesData });
        });
        this.setState({ toast: this.toast });    
    }

    loadRoomsByHotel(hotelId) {
    const idHotel = parseInt(hotelId, 10);
    this.hotelService.getAllHabitaciones(idHotel).then((data) => {
        const habitacionesData = data.map((habitacion) => ({
            value: habitacion.iden, // Usar el ID como value
            label: habitacion.numero,
        }));
        this.setState({ habitaciones: habitacionesData });
        });
    }

    onHotelSelectChange = (e) => {
        const selectedHotelId = e.value;
        this.setState({
            hotelSeleccionado: selectedHotelId,
        });
        this.loadRoomsByHotel(selectedHotelId);
    };

    guardadHotel() {
        this.state.reserva.habitaciones = 5;
        this.reservasService.save2(this.state.reserva).then(data => {
            this.setState({
                visible: false
            });
            this.reservasService.getAll().then(data => this.setState({ reservas: data }))
        })
    }

    eliminarHotel() {
        if (window.confirm("Desea Eliminar registro")) {
            this.reservasService.eliminar(this.state.hotelSeleccionado.iden).then(data => {
                this.state.toast.show({
                    severity: 'success',
                    summary: 'Éxito',
                    detail: 'Hotel Eliminado correctamente',
                });
                this.reservasService.getAll().then(data => this.setState({ reservas: data }))
            });
        }
    }
    render() {
        return (
            <div style={{ width: '80%', marginTop: '5%', margin: '0 auto' }}>
                <Menubar model={this.items} />
                <br />
                <Panel header="RESERVAS"  >
                    <DataTable value={this.state.reservas} selectionMode="single" selection={this.state.hotelSeleccionado} onSelectionChange={(e) => this.setState({ hotelSeleccionado: e.value })}>
                        <Column field='iden' header="ID"></Column>
                        <Column field='entrada' header="ENTRADA"></Column>
                        <Column field='salida' header="SALIDA"></Column>
                        <Column field='usuario' header="USUARIO-ID"></Column>
                    </DataTable>
                </Panel>
                <Dialog header="RESERVAR HABITACION" visible={this.state.visible} style={{ width: '50%' }} footer={this.footer} modal={true} onHide={() => this.setState({ visible: false })}>
                    <form id='reserva-form'>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                            <InputText value={this.state.reserva.entrada} id='entrada' onChange={(e) => {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let reserva = Object.assign({}, prevState.reserva);
                                    reserva.entrada = valor
                                    return { reserva }
                                })
                            }
                            } />
                            <label htmlFor='Entrada'>Entrada</label>
                        </span>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                            <InputText value={this.state.reserva.salida} id='salida' onChange={(e) => {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let reserva = Object.assign({}, prevState.reserva);
                                    reserva.salida = valor
                                    return { reserva }
                                })
                            }
                            } />
                            <label htmlFor='Salida'>Salida</label>
                        </span>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                            <InputNumber inputId="integeronly" value={this.state.reserva.cliente} onValueChange={(e) => 
                            {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let reserva = Object.assign({}, prevState.reserva);
                                    reserva.cliente = valor
                                    return { reserva }
                                })
                            }
                            } />
                            <label htmlFor='Cliente'>ID Ciente</label>
                        </span>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                        <div className="card flex justify-content-center">
                            <div className="card flex justify-content-center">
                            <TreeSelect
                                value={this.state.hotelSeleccionado}
                                onChange={this.onHotelSelectChange}
                                options={this.state.hoteles}
                                metaKeySelection={false}
                                placeholder="Select Hotel"
                            />
                            {this.state.habitaciones.length > 0 && (
                                <div className="card flex justify-content-center">
                                <TreeSelect
                                    value={this.state.habitacionesSeleccionadas}
                                    onChange={(e) =>{
                                        this.setState({
                                            habitacionesSeleccionadas : e.value,
                                        });
                                    }
                                    }
                                    options={this.state.habitaciones}
                                    metaKeySelection={false}
                                    placeholder="Select Rooms"
                                />
                                </div>
                            )}
                            </div>
                        </div>
                        </span>
                    </form>
                </Dialog>
                
                <Toast ref={(el) => (this.toast = el)} />
            </div>
        );
    }
    mostrarDialogo() {
        this.setState({
            visible: true,
            reserva: {
                iden: null,
                entrada: null,
                salida: null,
                cliente: 0,
                habitaciones:0,
            },
            hotel: {
                iden: null,
                nombre: null,
                descripcion: null,
                direccion: null,
            },
            toast: null,
            habitaciones: [],
            habitacionesSeleccionadas:{},
            reservaSeleccionado: {},
            hotelSeleccionado: {},
        });
    }

    mostrarEditar() {
        this.setState({
            visible: true,
            reserva: {
                iden: this.state.hotelSeleccionado.iden,
                entrada: this.state.hotelSeleccionado.entrada,
                salida: this.state.hotelSeleccionado.salida,
                cliente: this.state.hotelSeleccionado.cliente,
                habitaciones: 5,
            }

        })
    }



}