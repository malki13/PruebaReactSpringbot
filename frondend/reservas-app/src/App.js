import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Cliente from './pages/Cliente'
import Reservas from './pages/Reservas'
import Menum from './components/Menu'
import Hoteles from './pages/Hoteles'

export default function App(){
  return(
    <div>
        <Routes>
          <Route path='/' element={<Menum/>}>
            <Route path='cliente' element={<Cliente/>} />
            <Route path='reservas' element={<Reservas/>} />
            <Route path='hoteles' element={<Hoteles/>} />
            <Route path='*' element={<Hoteles/>} />
          </Route>
        </Routes>

    </div>
  )
}