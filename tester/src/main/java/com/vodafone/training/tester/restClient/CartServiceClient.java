package com.vodafone.training.tester.restClient;

import com.vodafone.training.tester.domainObjects.Cart;
import com.vodafone.training.tester.domainObjects.CartInput;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

public class CartServiceClient {

    public static void main(String... args) {
            RestTemplate restTemplate = new RestTemplate();
            CartInput cartInput = new CartInput(13, 2);
            System.out.println(cartInput.toString());

            String jwtToken ="eyJraWQiOiJjMzVhNjIyOS05ZmVkLTRjODgtYTdhNC03ZDcyNjRlNzVlODUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJtYXJrIiwidXNlcl9lbWFpbCI6ImhhcmlzaHJjYWRkZXNpZ25lckBnbWFpbC5jb20iLCJ1c2VyX25hbWUiOiJtYXJrIiwicm9sZXMiOlsiQ1VTVE9NRVIiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDAwIiwiYXVkIjoidm9kYWZvbmUtZWNvbW1lcmNlLWNsaWVudCIsIm5iZiI6MTcxMDM5MjU4NCwidXNlcl9pZCI6Im1hcmtfc21pdGgiLCJ1c2VyX21vYmlsZSI6Ijg3NjU0MzIxMDkiLCJzY29wZSI6WyJvcGVuaWQiXSwiZXhwIjoxNzEwMzkyODg0LCJpYXQiOjE3MTAzOTI1ODQsImp0aSI6ImU0YTE2NDZhLTQyZGQtNDk0Mi1hMzhjLTQ1YzJlNzAxMDFiZSJ9.Ruq7qUEyuOPwdFAeEdDTJWuvK5J2hi9V_r9qJsqmE9MObye49wiqzYvLeyX93W96u9DwURBuDykAw6vVOsQtyTmDmCvXEOhCV1cjg4NShcZ5Pgmb2LSSh1CbbiQKJDhZ0WwD-xuKfqNrT5sXkpPsiCbqguDXHac-5dlgck0Nw0xNYovg9CPcAu5gwT8NMDyvkhjxCzpONuhSIv67ugsCAO4iVTLFARty40pE18rl4XtnofhDmHrZ6UKemLs-DMZTgchRcvhXhID9ENrKakF9jFL4i_tibvDqDXaSRqfB8whbytQGsOr805AfVfPWJ-MgyI0QVTTksRjQ-EXVF5AYkw";
            String tokenHeader = "Bearer " + jwtToken;


            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", tokenHeader);

            HttpEntity<CartInput> entity = new HttpEntity<>(cartInput, headers);

            ResponseEntity<Cart> responseEntity = restTemplate.exchange("http://localhost:8082/cart/add", HttpMethod.POST, entity, Cart.class);
            Cart result = responseEntity.getBody();
            System.out.println(result.toString());

    }

}
