package com.sanctionco.opconnect;

import com.sanctionco.opconnect.model.Filter;
import com.sanctionco.opconnect.model.Item;
import com.sanctionco.opconnect.model.Patch;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OPConnectVaultClientTest {

  @Test
  void vaultClientPassesCallsThrough() {
    final OPConnectClient client = mock(OPConnectClient.class);
    final Item item = Item.builder().build();
    final Patch patch = Patch.builder().build();

    OPConnectVaultClient vaultClient = new OPConnectVaultClient(client, "testId");

    vaultClient.getVault();
    verify(client).getVault(eq("testId"));

    vaultClient.listItems();
    verify(client).listItems(eq("testId"));

    vaultClient.listItems("filter");
    verify(client).listItems(eq("testId"), eq("filter"));

    vaultClient.listItems(Filter.title().present());
    verify(client).listItems(eq("testId"), eq("title pr"));

    vaultClient.getItem("testItemId");
    verify(client).getItem(eq("testId"), eq("testItemId"));

    vaultClient.createItem(item);
    verify(client).createItem(eq("testId"), same(item));

    vaultClient.replaceItem("itemId", item);
    verify(client).replaceItem(eq("testId"), eq("itemId"), same(item));

    vaultClient.patchItem("itemId", Collections.singletonList(patch));
    verify(client)
        .patchItem(eq("testId"), eq("itemId"), eq(Collections.singletonList(patch)));

    vaultClient.patchItem("itemId", patch);
    verify(client).patchItem(eq("testId"), eq("itemId"), same(patch));

    vaultClient.deleteItem("itemId");
    verify(client).deleteItem(eq("testId"), eq("itemId"));

    vaultClient.listFiles("itemId");
    verify(client).listFiles(eq("testId"), eq("itemId"));

    vaultClient.listFiles("itemId", true);
    verify(client).listFiles(eq("testId"), eq("itemId"), eq(true));

    vaultClient.getFile("itemId", "fileId");
    verify(client).getFile(eq("testId"), eq("itemId"), eq("fileId"));

    vaultClient.getFile("itemId", "fileId", true);
    verify(client).getFile(eq("testId"), eq("itemId"), eq("fileId"), eq(true));
  }
}
