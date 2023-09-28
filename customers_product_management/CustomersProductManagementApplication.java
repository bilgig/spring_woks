package com.salessite.management;

import com.salessite.management.model.Customer;
import com.salessite.management.model.OrderDetail;
import com.salessite.management.model.Product;
import com.salessite.management.repository.CustomerRepository;
import com.salessite.management.repository.OrderDetailRepository;
import com.salessite.management.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor//classın constructurenı otomatik yaratır.
public class CustomersProductManagementApplication implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;

    public static void main(String[] args) {
        SpringApplication.run(CustomersProductManagementApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
      //Ürünü alan müşteri listesini verir.
     Scanner scanner = new Scanner(System.in);
     System.out.println("Ürün giriniz: ");
     String productName=scanner.next();

     Long productId =  productRepository.findByProductName (productName)
             .orElseThrow(() -> new RuntimeException("no product")).getId();

      List<OrderDetail> orderDetails = orderDetailRepository.findByProductId(productId);
      List<Long> customerIdList = orderDetails.stream()
              .map(OrderDetail::getCustomerId)
              .toList();

      List<Customer> customerList = customerRepository.findAllById(customerIdList);
      System.out.println("-----------------Ürünü alam müşterilerin adı---------------");
      System.out.println("");
      System.out.println("--Müşteri adı--");
      for(Customer customer : customerList) {
        System.out.println(customer.getFirstName());
      }
      System.out.println("");

      System.out.println();
    //Girilen fiyattan fazla yada eşit olan ürünler gelir.
      System.out.println("Fiyat giriniz: ");
      double productPrice = scanner.nextDouble();

      List<Product> productList = productRepository.findProductsByPriceJPQL(productPrice);

      System.out.println("-----------------Ürün Listesi---------------");
      System.out.println("");
      System.out.println("--Ürün ismi--                    ----Fiyatı");
      for (Product product : productList) {
        System.out.print(product.getProductName());
        System.out.print("                                  ");
        System.out.println(product.getPrice());
      }
      System.out.println("");


//Tüm müşteri listesini gelir.
      System.out.println("Müşterilerimiz ve Ürünlerimiz");
      List<Customer> customertList = customerRepository.findAll();
      System.out.println("-----------------Müşteri Listesi---------------");
      System.out.println("");
      System.out.println("--Müşteri adı--                          --ActiveCustomer?--");
      for(Customer customer : customertList) {
        System.out.println();
        System.out.print(customer.getFirstName());
        System.out.print("                                      ");
        System.out.print(customer.isAcive());
      }
      System.out.println("");
//Tüm ürün listesi gelir
      List<Product> allProductList = productRepository.findAll();
      System.out.println("-----------------Ürün Listesi---------------");
      System.out.println("");
      System.out.println("--İsim--                          --Miktarı--                ----Fiyatı");
      for(Product product : allProductList) {
        System.out.print(product.getProductName());
        System.out.print("                                  ");
        System.out.print(product.getProductQuantity());
        System.out.print("                                  ");
        System.out.println(product.getPrice());
      }
      System.out.println("");
    }

    //Consoldan OrderDetail tablosuna müşteri id ve ürün id kayıtlarını ekleme
  private void addAProductToCustomer() {
    /*Scanner scanner = new Scanner(System.in);
    System.out.println("Müşteri Adını Giriniz:");
    String customerName = scanner.next();
    System.out.println("Alınacak Ürün Adı Giriniz");
    String productName = scanner.next();
    System.out.println();
    Long customerId = customerRepository.findByFirstName(customerName)
            .orElseThrow(()-> new RuntimeException("no customer"))
            .getId();
    Long productId = productRepository.findByProductName(productName)
            .orElseThrow(() -> new RuntimeException("no lesson")).getId();

    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setCustomerId(customerId);
    orderDetail.setProductId(productId);
    //lessonDetailRepository.save(lessonDetail);*/

  }

  //Direkt database Müşter, product ve orderdetail tablolarına kayıt ekleme
    public void insertScript(){
      Customer customer1 = new Customer();
      customer1.setId(1L);
      customer1.setFirstName("Bilgi");
      customer1.setLastName("Özdemir");
      customer1.setAddress("Gaziantep");
      customer1.setPhone("5305289398");
      customer1.setEmail("bilgig@hotmail.com");
      customer1.setAcive(true);

      Customer customer2 = new Customer();
      customer2.setId(2L);
      customer2.setFirstName("Gökay");
      customer2.setLastName("Özdemir");
      customer2.setAddress("Muş");
      customer2.setPhone("5306560777");
      customer2.setEmail("gokayozdemir49@hotmail.com");
      customer2.setAcive(true);

      Customer customer3 = new Customer();
      customer3.setId(3L);
      customer3.setFirstName("Fatih");
      customer3.setLastName("Özdemir");
      customer3.setAddress("Ankara");
      customer3.setPhone("5305289398");
      customer3.setEmail("fatihozdemir@hotmail.com");
      customer3.setAcive(true);


      Customer customer4 = new Customer();
      customer4.setId(4L);
      customer4.setFirstName("Doğa");
      customer4.setLastName("Özdemir");
      customer4.setAddress("Ankara");
      customer4.setPhone("5306569398");
      customer4.setEmail("dogaozdemir@hotmail.com");
      customer4.setAcive(false);

      Customer customer5 = new Customer();
      customer5.setId(5L);
      customer5.setFirstName("Deniz");
      customer5.setLastName("Güneş");
      customer5.setAddress("Bursa");
      customer5.setPhone("5558966363");
      customer5.setEmail("denizgunes@hotmail.com");
      customer5.setAcive(true);

      Product product1 = new Product();
      product1.setId(1L);
      product1.setProductName("Lenova Labtop");
      product1.setProductQuantity(5);
      product1.setPrice(5000.00);
      product1.setStatus(true);

      Product product2 = new Product();
      product2.setId(2L);
      product2.setProductName("Iphone Watch");
      product2.setProductQuantity(5);
      product2.setPrice(2000.00);
      product2.setStatus(true);

      Product product3 = new Product();
      product3.setId(3L);
      product3.setProductName("Samsung Telephone");
      product3.setProductQuantity(5);
      product3.setPrice(7000.00);
      product3.setStatus(true);

      Product product4 = new Product();
      product4.setId(4L);
      product4.setProductName("Hp Printer");
      product4.setProductQuantity(5);
      product4.setPrice(6000.00);
      product4.setStatus(true);

      Product product5 = new Product();
      product5.setId(1L);
      product5.setProductName("Lenova Mouse");
      product5.setProductQuantity(5);
      product5.setPrice(4000.00);
      product5.setStatus(true);

      OrderDetail orderDetail1 = new OrderDetail();
      orderDetail1.setCustomerId(1L);
      orderDetail1.setProductId(2L);

      OrderDetail orderDetail2 = new OrderDetail();
      orderDetail2.setCustomerId(2L);
      orderDetail2.setProductId(1L);

      OrderDetail orderDetail3 = new OrderDetail();
      orderDetail3.setCustomerId(3L);
      orderDetail3.setProductId(5L);

      OrderDetail orderDetail4 = new OrderDetail();
      orderDetail4.setCustomerId(5L);
      orderDetail4.setProductId(4L);

      OrderDetail orderDetail5 = new OrderDetail();
      orderDetail5.setCustomerId(4L);
      orderDetail5.setProductId(3L);

      OrderDetail orderDetail6 = new OrderDetail();
      orderDetail6.setCustomerId(1L);
      orderDetail6.setProductId(4L);

      customerRepository.save(customer1);
      customerRepository.save(customer2);
      customerRepository.save(customer3);
      customerRepository.save(customer4);
      customerRepository.save(customer5);

      productRepository.save(product1);
      productRepository.save(product2);
      productRepository.save(product3);
      productRepository.save(product4);
      productRepository.save(product5);

      orderDetailRepository.save(orderDetail1);
      orderDetailRepository.save(orderDetail2);
      orderDetailRepository.save(orderDetail3);
      orderDetailRepository.save(orderDetail4);
      orderDetailRepository.save(orderDetail5);
      orderDetailRepository.save(orderDetail6);
     }

    }

