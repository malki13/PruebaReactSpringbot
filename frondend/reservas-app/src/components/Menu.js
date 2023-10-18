import { Link, Outlet } from "react-router-dom"
import React from 'react'; 
import { Menu } from 'primereact/menu';
import { Menubar } from 'primereact/menubar';

import 'primereact/resources/themes/nova-alt/theme.css'
import 'primereact/resources/primereact.min.css'
import 'primeicons/primeicons.css'

// export default function BasicDemo() {
//     let items = [
//         {label: 'New', icon: 'pi pi-fw pi-plus'},
//         {label: 'Delete', icon: 'pi pi-fw pi-trash'}
//     ];

//     return (
//         <Menu model={items} />
//     )
// }

const Menum = () =>{
    const itmsd = [
        {
            label:"cliente",
            icon: 'pi pi-user pi-user',
            url:'/cliente'
        },
        {
            label:"reservas",
            icon: 'pi pi-external-link',
            url:'/reservas'
        },
        {
            label:"Hoteles",
            icon: 'pi pi-external-link',
            url:'/hoteles'
        },
    ];
    return(
        <div>
            <Menubar model={itmsd} />
            
            <Outlet />
        </div>
    )
}

export default Menum;