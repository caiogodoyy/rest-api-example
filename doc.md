Controllers: classes que mapeiam as solicitações http para métodos específicos, processam dados e retornam as respostas. Controllers são anotados com @RestController indicando que são de uma API Rest e com @RequestMapping para definir o mapeamento da URL.

DTO (Data Transfer Object): objetos que transportam dados entre diferentes partes do sistema.

JPA (Java Persistence API): especificação que define uma interface para mapear objetos Java para e um banco de dados. Facilita a interação com o banco de dados, permitindo que trabalhe com objetos Java em vez de consultas SQL diretamente.

Repository: são interfaces que estendem JpaRepository, fornecendo métodos para interagir com o banco de dados, como salvar, recuperar, atualizar ou excluir registros. Os métodos dos repositórios podem ser personalizados ou gerados automaticamente, com base em convenções de nomenclatura.
```Java
User findByUsername(String username);
```
O Spring Data JPA reconhecerá a convenção findBy e usará o restante do nome do método (Username) para construir a consulta SQL correspondente.

Fluxo de autenticação:
1. Cliente faz requisição POST no */login* fornecendo seus dados no corpo.
2. No controller é criado um objeto *UsernamePasswordAuthenticationToken* com os dados fornecidos.
3. O objeto *AuthenticationManager* instanciado com *@Autowired* é usado pra autenticar esse token. Internamente, o Spring Security usa o *UserDetailsService* que faz a verificação e retorna um objeto *UserDetails* que contém informações sobre o usuário.
4. O controller chama o *TokenService* para criar um token de acesso e o retorna para o cliente armazenar e usá-lo nas solicitações futuras.
5. O *SecurityConfigurations* define as regras de segurança para a aplicação.

SecurityFilter:
1. Quando uma solicitação HTTP é recebida pelo servidor, ela passa por uma série de filtros, incluindo o SecurityFilter, antes de chegar ao controlador apropriado.
2. O SecurityFilter é configurado para ser executado uma vez por solicitação HTTP com a anotação *@OncePerRequestFilter*.
3. O Spring Security garante que o SecurityFilter seja executado antes do filtro padrão de autenticação, devido à configuração feita no método securityFilterChain da classe SecurityConfigurations, no método addFilterBefore.
4. Ao ser executado, ele verifica o cabeçalho Authorization da solicitação para encontrar o token de autenticação.
5. Com o token extraído, o SecurityFilter usa o TokenService para verificar e autenticar o usuário com base nas informações do token.
6. Se o token for válido e corresponder a um usuário autenticado, o SecurityFilter configura a autenticação no contexto de segurança do Spring Security usando SecurityContextHolder.getContext().setAuthentication(authentication).
7. Após a autenticação bem-sucedida, a solicitação continua a ser processada, e o controlador de login lida com a solicitação.

#

### Os controllers recebem as solicitações HTTP, usam DTOs para transportar dados, interagem com os banco de dados por meio de repositórios JPA e, eventualmente, retornam respostas ao cliente. As anotações ajudam a configurar o comportamento e a estrutura da API.
