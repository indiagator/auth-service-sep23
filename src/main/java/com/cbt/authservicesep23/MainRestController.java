package com.cbt.authservicesep23;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class MainRestController {

    private final CredentialRepository credentialRepository;

    private final UserdetailRepository userdetailRepository;

    private final UsertypelinkRepository usertypelinkRepository;

    private final UserdetailService userdetailService;

    private final WebClient.Builder webclient;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody Credential credential)
    {
        credentialRepository.save(credential);

        return ResponseEntity.ok().body("New Signup Successful");
    }

    @PostMapping("save/user/detail")
    public ResponseEntity<Userdetail> saveUserDetails(@RequestBody Userdetail userdetail)
    {
        userdetailRepository.save(userdetail);
        return ResponseEntity.ok().body(userdetail);
    }

    @PostMapping("save/user/type")
    public ResponseEntity<Usertypelink> saveUserType(@RequestBody Usertypelink usertypelink)
    {
        usertypelink.setLinkid(String.valueOf((int)(Math.random()*100000)));
        usertypelinkRepository.save(usertypelink);
        return ResponseEntity.ok().body(usertypelink);
    }

    @GetMapping("get/user/detail/{username}")
    public ResponseEntity<FullUserDetail> getUserDetail(@PathVariable("username") String username) {

        return ResponseEntity.ok().body(userdetailService.getFullUserDetail(username));
    }

    @GetMapping("forward/admin/1")
    public ResponseEntity<Mono<String>> forwardToAdminService()
    {
        Mono<String> adminResp =  webclient.build().get().uri("http://localhost:8072/admin-service/api/v1/revert/auth/1").retrieve().bodyToMono(String.class);
        return ResponseEntity.ok(adminResp);
    }

    @GetMapping("end/response")
    public ResponseEntity<String> endResponse()
    {
        return ResponseEntity.ok("This is the END_RESPONSE");
    }


}
