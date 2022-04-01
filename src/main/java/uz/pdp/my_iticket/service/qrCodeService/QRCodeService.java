package uz.pdp.my_iticket.service.qrCodeService;

public interface QRCodeService {
    byte[] generateQRCode(String qrContent, int width, int height);
}
