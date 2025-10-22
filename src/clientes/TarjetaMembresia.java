package clientes;

public class TarjetaMembresia {

    protected String codigo;
    protected Membresia membresia;

    public enum Membresia{
        cobre(0.05),
        plata(0.10),
        oro(0.25);

        private double descuento;

        Membresia(double descuento){
            this.descuento = descuento;
        }

        public double getDescuento(){
            return descuento;
        }
    }
}
