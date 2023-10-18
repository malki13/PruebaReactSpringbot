import { Component } from 'react';
// import { HotelService } from '../services/HotelReservas';
import { ClienteService } from '../services/ClienteReservas';
import { DataTable } from 'primereact/datatable'
import { Column } from 'primereact/column';
import { Panel } from 'primereact/panel';
import { Menubar } from 'primereact/menubar';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';

import 'primereact/resources/themes/nova-alt/theme.css'
import 'primereact/resources/primereact.min.css'
import 'primeicons/primeicons.css'


export default class Hoteles extends Component {

    constructor() {
        super();

        this.state = {
            visible: false,
            cliente: {
                iden: null,
                nombre: null,
                correo: null,
                telefono: null,
            },
            toast: null,
            hotelSeleccionado: {
            }
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
        this.clienteService = new ClienteService();
        this.guardadHotel = this.guardadHotel.bind(this);
        this.eliminarHotel = this.eliminarHotel.bind(this)

        this.footer = (
            <div>
                <Button label='Guardar' icon='pi pi-check' onClick={this.guardadHotel} />
            </div>
        );

    }

    componentDidMount() {
        this.clienteService.getAll().then(data => this.setState({ clientes: data }))
        this.setState({ toast: this.toast });

    }

    guardadHotel() {
        this.clienteService.save(this.state.cliente).then(data => {
            this.setState({
                visible: false
            });
            this.state.toast.show({
                severity: 'success',
                summary: 'Éxito',
                detail: 'Cliente guardado correctamente',
            });
            this.clienteService.getAll().then(data => this.setState({ clientes: data }))
        })
    }

    eliminarHotel() {
        if (window.confirm("Desea Eliminar registro")) {
            this.clienteService.eliminar(this.state.hotelSeleccionado.iden).then(data => {
                this.state.toast.show({
                    severity: 'success',
                    summary: 'Éxito',
                    detail: 'Cliente Eliminado correctamente',
                });
                this.clienteService.getAll().then(data => this.setState({ clientes: data }))
            });
        }
    }

    render() {
        return (
            <div style={{ width: '80%', marginTop: '5%', margin: '0 auto' }}>
                <Menubar model={this.items} />
                <br />
                <Panel header="CLIENTES" >
                    <DataTable value={this.state.clientes} selectionMode="single" selection={this.state.hotelSeleccionado} onSelectionChange={(e) => this.setState({ hotelSeleccionado: e.value })}>
                        <Column field='iden' header="ID"></Column>
                        <Column field='nombre' header="NOMBRE"></Column>
                        <Column field='correo' header="CORREO"></Column>
                        <Column field='telefono' header="TELEFONO"></Column>
                    </DataTable>
                </Panel>
                <Dialog header="Crear Hotel" visible={this.state.visible} style={{ width: '50%' }} footer={this.footer} modal={true} onHide={() => this.setState({ visible: false })}>
                    <form id='persona-form'>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                            <InputText value={this.state.cliente.nombre} id='nombre' onChange={(e) => {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let cliente = Object.assign({}, prevState.cliente);
                                    cliente.nombre = valor
                                    return { cliente }
                                })
                            }
                            } />
                            <label htmlFor='Nombre'>Nombre</label>
                        </span>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                            <InputText value={this.state.cliente.correo} id='correo' onChange={(e) => {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let cliente = Object.assign({}, prevState.cliente);
                                    cliente.correo = valor
                                    return { cliente }
                                })
                            }
                            } />
                            <label htmlFor='Correo'>Correo</label>
                        </span>
                        <span style={{ marginBottom: '4%' }} className='p-float-label'>
                            <InputText value={this.state.cliente.telefono} id='telefono' onChange={(e) => {
                                let valor = e.target.value;
                                this.setState(prevState => {
                                    let cliente = Object.assign({}, prevState.cliente);
                                    cliente.telefono = valor
                                    return { cliente }
                                })
                            }
                            } />
                            <label htmlFor='Telefono'>Telefono</label>
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
            cliente: {
                iden: null,
                nombre: null,
                correo: null,
                telefono: null,
            }
        });
        // document.getElementById('persona-form').reset();
    }

    mostrarEditar() {
        this.setState({
            visible: true,
            cliente: {
                iden: this.state.hotelSeleccionado.iden,
                nombre: this.state.hotelSeleccionado.nombre,
                correo: this.state.hotelSeleccionado.correo,
                telefono: this.state.hotelSeleccionado.telefono,
            }

        })
    }



}