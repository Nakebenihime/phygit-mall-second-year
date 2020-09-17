package pds.qflush.apiqflush.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Long iduser;
    private List<CartLine>  cartLines = new ArrayList<CartLine>();
    private double total;

    public Cart(Long iduser) {
        this.iduser = iduser;
    }

    public Long getIduser() {
        return iduser;
    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public CartLine findOneProduct(long id){
        for (CartLine productInCard : this.cartLines) {
            if (productInCard.getId().equals(id)) {
                return productInCard;
            }
        }
        return null;

    }

    public void addProduct(CartLine item) {
        CartLine lineInCart = this.findOneProduct(item.getId());
        // si le produit n'est pas dans le panier on en crée une ligne
        if (lineInCart == null) {
            lineInCart = item;
            this.cartLines.add(lineInCart);
        }else {
            int quantityUpdate = lineInCart.getQuantity() + 1;
            lineInCart.setQuantity(quantityUpdate);
        }
        this.setTotal();
        //sinon on augmennte la quantité du produit dans le panier de 1;

    }

    public double getTotal() {
        return total;
    }

    public void setTotal(){
        double tempToal =0;
        for (CartLine cartLine:this.getCartLines()){
            tempToal+=cartLine.getQuantity()*cartLine.getPrice();
        }
        this.total=tempToal;
    }

    public void removeProduct(CartLine cartLine) {
        CartLine line = this.findOneProduct(cartLine.getId());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }
    public void updateProductQuantity(Long id, int quantity) {
        CartLine line = this.findOneProduct(id);
        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }
}
