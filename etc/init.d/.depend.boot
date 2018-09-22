TARGETS = console-setup mountkernfs.sh alsa-utils ufw plymouth-log hostname.sh dns-clean pppd-dns apparmor x11-common udev keyboard-setup resolvconf mountdevsubfs.sh brltty procps networking urandom hwclock.sh checkroot.sh mountall-bootclean.sh mountall.sh bootmisc.sh checkfs.sh checkroot-bootclean.sh kmod mountnfs-bootclean.sh mountnfs.sh
INTERACTIVE = console-setup udev keyboard-setup checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
resolvconf: dns-clean
mountdevsubfs.sh: mountkernfs.sh udev
brltty: mountkernfs.sh udev
procps: mountkernfs.sh udev
networking: resolvconf mountkernfs.sh urandom dns-clean procps
urandom: hwclock.sh
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
mountall-bootclean.sh: mountall.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh
bootmisc.sh: mountall-bootclean.sh udev mountnfs-bootclean.sh checkroot-bootclean.sh
checkfs.sh: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
kmod: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
