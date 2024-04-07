package muon.app;

import org.junit.Test;
import org.junit.Before;

import muon.app.ssh.CachedCredentialProvider;
import muon.app.ssh.InputBlocker;
import muon.app.ssh.OperationCancelledException;
import muon.app.ssh.SshClient2;
import muon.app.ui.components.session.SessionInfo;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class SshClient2AuthPasswordTest {

    private SshClient2 sshClient;
    private SessionInfo sessionInfo;
    private InputBlocker inputBlocker;
    private CachedCredentialProvider cachedCredentialProvider;

    @Before
    public void setUp() {
        sessionInfo = mock(SessionInfo.class);
        inputBlocker = mock(InputBlocker.class);
        cachedCredentialProvider = mock(CachedCredentialProvider.class);
        
        sshClient = new SshClient2(sessionInfo, inputBlocker, cachedCredentialProvider);
    }

    @Test
    public void testAuthPasswordWithValidCredentials() throws Exception {
        // Arrange
        when(sessionInfo.getUser()).thenReturn("validUser");
        when(cachedCredentialProvider.getCachedPassword()).thenReturn("validPassword".toCharArray());

        // Act
        sshClient.authPassoword();

        // Assert
        assertTrue(sshClient.isConnected());
    }

    @Test(expected = OperationCancelledException.class)
    public void testAuthPasswordWithNullUserAndPassword() throws Exception {
        // Arrange
        when(sessionInfo.getUser()).thenReturn(null);
        when(cachedCredentialProvider.getCachedPassword()).thenReturn(null);

        // Act
        sshClient.authPassoword();
    }

    @Test(expected = OperationCancelledException.class)
    public void testAuthPasswordWithEmptyUserAndPassword() throws Exception {
        // Arrange
        when(sessionInfo.getUser()).thenReturn("");
        when(cachedCredentialProvider.getCachedPassword()).thenReturn("".toCharArray());

        // Act
        sshClient.authPassoword();
    }

    @Test(expected = Exception.class)
    public void testAuthPasswordWithErrorHandling() throws Exception {
        // Arrange
        when(sessionInfo.getUser()).thenReturn("validUser");
        when(cachedCredentialProvider.getCachedPassword()).thenReturn("invalidPassword".toCharArray());

        // Act
        sshClient.authPassoword();
    }
}