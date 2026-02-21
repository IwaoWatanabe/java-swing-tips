//Java 8+
//FILES ../resources/tips.properties

package example;

public final class tips {
static java.io.PrintStream out = System.out;

static java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("tips");
static String getTitle(String key) {
    if (key.indexOf(".") < 0) key += ".title";
    return rb.getString(key); }


void tlog(Object msg) { if (msg == null) return;
out.println(msg.toString()); }

boolean empty(String t) { return t == null || t.trim().length() == 0; }

String prop(String key, String prefix) {
if (empty(prefix)) return System.getProperty(key);
return prefix + System.getProperty(key); }

void fetchVersion() {
tlog(prop("java.version", "JRE version: "));
tlog(prop("java.vendor", "Vendor:"));
tlog(prop("java.vm.name", "VM Name: "));
tlog(prop("os.name", "OS: ") + " " +
    prop("os.version","") +
    prop("os.arch"," (") + ")");
tlog(prop("file.encoding", "File Encoding: "));
tlog(prop("user.name", "User Name: "));
try {
    tlog(prop("user.home", "Home: "));
    tlog(prop("java.home", "java home: "));
    tlog(prop("user.dir", "wd: "));
    tlog(prop("java.io.tmpdir", "temp dir: "));
} catch(SecurityException ignore) {}
try {
    tlog(prop("user.language", "Language: "));
    tlog(prop("user.country", "Country: "));
    tlog(prop("user.timezone", "Timezone: "));
} catch(SecurityException ignore) {}}

public static void main(String[] args) {
    new tips().fetchVersion();
    }
    // out.println(getTitle("NumberEditor")); }

} // end of class tips
